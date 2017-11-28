package jaro.ev3dev;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.math.BigDecimal;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Validator {

    public static final ParameterPredicate<Object> MANDATORY_PREDICATE = new ParameterPredicate<Object>("is mandatory") {
        @Override
        public boolean test(final Object t) {
            return (t != null);
        }
    };
    public static final ParameterPredicate<String> NON_BLANK_PREDICATE = new ParameterPredicate<String>("must be non-empty") {
        @Override
        public boolean test(final String t) {
            return (t != null && ! StringUtils.isBlank(t));
        }
    };
    public static final ParameterPredicate<Number> GT_ZERO_PREDICATE  = new ParameterPredicate<Number>("must be > 0") {
        @Override
        public boolean test(final Number t) {
            if (t == null) {
                return false;
            } else if (t instanceof Integer) {
                return (((Integer)t).intValue() > 0);
            } else if (t instanceof Long) {
                return (((Long)t).longValue() > 0);
            } else if (t instanceof Double) {
                return (((Double)t).doubleValue() > 0.0);
            } else if (t instanceof BigDecimal) {
                return (((BigDecimal)t).doubleValue() > 0.0);
            } else {
                throw new IllegalArgumentException("Expected either Integer, Long or Double, got " + t.getClass().getSimpleName());
            }
        }
    };
    public static final ParameterPredicate<Number> GT_ZERO_OR_NULL_PREDICATE  = new ParameterPredicate<Number>("must be null or > 0") {
        @Override
        public boolean test(final Number t) {
            return (t == null ? true : GT_ZERO_PREDICATE.test(t));
        }
    };
    public static final ParameterPredicate<Optional<?>> GT_ZERO_OR_EMPTY_PREDICATE  = new ParameterPredicate<Optional<?>>("must be empty or > 0") {
        @Override
        public boolean test(final Optional<?> opt) {
            if (opt == null) {   // basically an invalid case
                return false;
            } else if (! opt.isPresent()) {   // the 'empty' case
                return true;
            } else {
                final Object o = opt.get();
                if (o instanceof Number) {
                    return GT_ZERO_PREDICATE.test((Number)o);
                } else {
                    System.err.format("Only Optional<Number> should be passed in here (got %s)%n", o.getClass().getSimpleName());
                    return false;
                }
            }
        }
    };
    public static final ParameterPredicate<Number> GE_ZERO_PREDICATE  = new ParameterPredicate<Number>("must be >= 0") {
        @Override
        public boolean test(final Number t) {
            if (t == null) {
                return false;
            } else if (t instanceof Integer) {
                return (((Integer)t).intValue() >= 0);
            } else if (t instanceof Long) {
                return (((Long)t).longValue() >= 0);
            } else if (t instanceof Double) {
                return (((Double)t).doubleValue() >= 0.0);
            } else if (t instanceof BigDecimal) {
                return (((BigDecimal)t).doubleValue() >= 0.0);
            } else {
                throw new IllegalArgumentException("Expected either Integer, Long or Double, got " + t.getClass().getSimpleName());
            }
        }
    };
    public static final ParameterPredicate<Number> GE_ZERO_PREDICATE_OR_NULL  = new ParameterPredicate<Number>("must be null or >= 0") {
        @Override
        public boolean test(final Number t) {
            return (t == null ? true : GE_ZERO_PREDICATE.test(t));
        }
    };
    public static final ParameterPredicate<Number> LT_ZERO_PREDICATE  = new ParameterPredicate<Number>("must be < 0") {
        @Override
        public boolean test(final Number t) {
            if (t == null) {
                return false;
            } else if (t instanceof Integer) {
                return (((Integer)t).intValue() < 0);
            } else if (t instanceof Long) {
                return (((Long)t).longValue() < 0);
            } else if (t instanceof Double) {
                return (((Double)t).doubleValue() < 0.0);
            } else if (t instanceof BigDecimal) {
                return (((BigDecimal)t).doubleValue() < 0.0);
            } else {
                throw new IllegalArgumentException("Expected either Integer, Long or Double, got " + t.getClass().getSimpleName());
            }
        }
    };
    public static final ParameterPredicate<Number> LT_ZERO_PREDICATE_OR_NULL  = new ParameterPredicate<Number>("must be null or < 0") {
        @Override
        public boolean test(final Number t) {
            return (t == null ? true : LT_ZERO_PREDICATE.test(t));
        }
    };
    public static final ParameterPredicate<Number> LE_ZERO_PREDICATE  = new ParameterPredicate<Number>("must be <= 0") {
        @Override
        public boolean test(final Number t) {
            if (t == null) {
                return false;
            } else if (t instanceof Integer) {
                return (((Integer)t).intValue() <= 0);
            } else if (t instanceof Long) {
                return (((Long)t).longValue() <= 0);
            } else if (t instanceof Double) {
                return (((Double)t).doubleValue() <= 0.0);
            } else if (t instanceof BigDecimal) {
                return (((BigDecimal)t).doubleValue() <= 0.0);
            } else {
                throw new IllegalArgumentException("Expected either Integer, Long or Double, got " + t.getClass().getSimpleName());
            }
        }
    };
    public static final ParameterPredicate<Number> LE_ZERO_PREDICATE_OR_NULL  = new ParameterPredicate<Number>("must be null or <= 0") {
        @Override
        public boolean test(final Number t) {
            return (t == null ? true : LE_ZERO_PREDICATE.test(t));
        }
    };
//    public static final ParameterPredicate<Iterable<?>> NON_EMPTY_PREDICATE = new ParameterPredicate<Iterable<?>>("must be non-empty") {
//        @Override
//        public boolean test(final Iterable<?> t) {
//            return (t != null && ! Iterables.isEmpty(t));
//        }
//    };
    public static final ParameterPredicate<Map<?, ?>> NON_EMPTY_MAP_PREDICATE = new ParameterPredicate<Map<?, ?>>("must be non-empty") {
        @Override
        public boolean test(final Map<?, ?> m) {
            return (m != null && ! m.isEmpty());
        }
    };
//    public static final ParameterPredicate<Iterable<?>> EMPTY_PREDICATE = new ParameterPredicate<Iterable<?>>("must be empty (or null)") {
//        @Override
//        public boolean test(final Iterable<?> t) {
//            return (t == null || Iterables.isEmpty(t));
//        }
//    };
    public static final ParameterPredicate<Double> ZERO_TO_ONE_INCLUSIVE_PREDICATE = new ParameterPredicate<Double>("must be in <0, 1>") {
        @Override
        public boolean test(final Double t) {
            return (t != null && t >= 0.0 && t <= 1.0);
        }
    };
    public static final ParameterPredicate<Double> ZERO_TO_ONE_EXCLUSIVE_PREDICATE = new ParameterPredicate<Double>("must be in (0, 1)") {
        @Override
        public boolean test(final Double t) {
            return (t != null && t > 0.0 && t < 1.0);
        }
    };
    public static final ParameterPredicate<Double> ZERO_TO_HUNDRED_INCLUSIVE_PREDICATE = new ParameterPredicate<Double>("must be in <0, 100>") {
        @Override
        public boolean test(final Double t) {
            return (t != null && t >= 0.0 && t <= 100.0);
        }
    };
    public static final ParameterPredicate<Double> ZERO_TO_HUNDRED_EXCLUSIVE_PREDICATE = new ParameterPredicate<Double>("must be in (0, 100)") {
        @Override
        public boolean test(final Double t) {
            return (t != null && t > 0.0 && t < 100.0);
        }
    };
    public static final ParameterPredicate<File> FILE_MUST_EXIST_AND_REGULAR_PREDICATE = new ParameterPredicate<File>("is mandatory, must exist and must be a regular file") {
        @Override
        public boolean test(final File t) {
            return (t != null && t.isFile());
        }
    };
    public static final ParameterPredicate<File> FILE_IF_EXISTS_THEN_REGULAR_PREDICATE = new ParameterPredicate<File>("is mandatory and is a regular file if exists") {
        @Override
        public boolean test(final File t) {
            return (t != null && (! t.exists() || t.isFile()));
        }
    };
    public static final ParameterPredicate<File> DIR_MUST_EXIST_PREDICATE = new ParameterPredicate<File>("is mandatory, must exist and must be a directory") {
        @Override
        public boolean test(final File t) {
            return (t != null && t.isDirectory());
        }
    };
    public static final ParameterPredicate<Boolean> TRUE_PREDICATE = new ParameterPredicate<Boolean>("must be True") {
        @Override
        public boolean test(final Boolean b) {
            return (b != null && b.booleanValue());
        }
    };
    public static final ParameterPredicate<Boolean> FALSE_PREDICATE = new ParameterPredicate<Boolean>("must be False") {
        @Override
        public boolean test(final Boolean b) {
            return (b != null && ! b.booleanValue());
        }
    };

    public static abstract class ParameterPredicate<T> implements Predicate<T> {
        private final String testDescription;

        public ParameterPredicate(final String testDescription) {
            this.testDescription = testDescription;
        }

        public String getTestDescription() {
            return this.testDescription;
        }
    }

    public static <T> void assertParameter(final String paramName, final T paramValue, final ParameterPredicate<T> condition) {
        if (! condition.test(paramValue)) {
            throw new IllegalArgumentException(String.format("Parameter %s %s (is %s)", paramName, condition.getTestDescription(), paramValue));
        }
    }
    public static <T> void assertParameter(final String paramName, final T paramValue, final List<ParameterPredicate<T>> conditions) {
        conditions.forEach((condition) -> {
            assertParameter(paramName, paramValue, condition);
        });
    }
    public static <T> void assertParameter(final Supplier<String> paramNameSupplier, final T paramValue, final ParameterPredicate<T> condition) {
        if (! condition.test(paramValue)) {
            throw new IllegalArgumentException(String.format("Parameter %s %s (is %s)", paramNameSupplier.get(), condition.getTestDescription(), paramValue));
        }
    }

    public static <T> void assertState(final T in, final Function<T, String> stateNameFunction, final boolean state, final boolean req) {
        if (state != req) {
            assertState(stateNameFunction.apply(in), state, req);  // i.e. only getting the state name if we know the exception will be thrown
        }
    }
    public static <T> void assertState(final Supplier<String> stateNameSupplier, final boolean state, final boolean req) {
        if (state != req) {
            assertState(stateNameSupplier.get(), state, req);  // i.e. only getting the state name if we know the exception will be thrown
        }
    }
    public static <T> void assertState(final String stateName, final boolean state, final boolean req) {
        if (state != req) {
            throw new IllegalStateException(String.format("State %s must be %s", stateName, StringUtils.capitalize(Boolean.toString(req))));
        }
    }

    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilExclusive(final Optional<T> sinceInclusive, final Optional<T> untilExclusive) {
        assertSinceInclusiveUntilExclusive(sinceInclusive.orElse(null), false, untilExclusive.orElse(null), false);
    }
    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilExclusive(final T sinceInclusive, final T untilExclusive) {
        assertSinceInclusiveUntilExclusive("sinceInclusive", sinceInclusive, true,
                        "untilExclusive", untilExclusive, true);
    }
    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilExclusive(
                    final T sinceInclusive, final boolean sinceMandatory,
                    final T untilExclusive, final boolean untilMandatory) {
        assertSinceInclusiveUntilExclusive("sinceInclusive", sinceInclusive, sinceMandatory,
                        "untilExclusive", untilExclusive, untilMandatory);
    }
    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilExclusive(
                    final String sinceParamName, final T sinceInclusive,
                    final String untilParamName, final T untilExclusive) {
        assertSinceInclusiveUntilExclusive(sinceParamName, sinceInclusive, true,
                        untilParamName, untilExclusive, true);
    }
    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilExclusive(
                    final String sinceParamName, final T sinceInclusive, final boolean sinceMandatory,
                    final String untilParamName, final T untilExclusive, final boolean untilMandatory) {

        final String sinceName = Optional.ofNullable(StringUtils.trimToNull(sinceParamName)).orElse("sinceInclusive");
        final String untilName = Optional.ofNullable(StringUtils.trimToNull(untilParamName)).orElse("untilExclusive");

        if (sinceMandatory) {
            assertParameter(sinceName, sinceInclusive, MANDATORY_PREDICATE);
        }
        if (untilMandatory) {
            assertParameter(untilName, untilExclusive, MANDATORY_PREDICATE);
        }
        if (sinceInclusive != null && untilExclusive != null) {
            assertParameter(() -> String.format("%s (%s) before %s (%s)", sinceName, sinceInclusive, untilName, untilExclusive),
                            sinceInclusive.compareTo(untilExclusive) < 0, TRUE_PREDICATE);
        }
    }

    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilInclusive(final Optional<T> sinceInclusive, final Optional<T> untilInclusive) {
        assertSinceInclusiveUntilInclusive(sinceInclusive.orElse(null), false, untilInclusive.orElse(null), false);
    }
    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilInclusive(final T sinceInclusive, final T untilInclusive) {
        assertSinceInclusiveUntilInclusive("sinceInclusive", sinceInclusive, true,
                        "untilInclusive", untilInclusive, true);
    }
    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilInclusive(
                    final T sinceInclusive, final boolean sinceMandatory,
                    final T untilInclusive, final boolean untilMandatory) {
        assertSinceInclusiveUntilInclusive("sinceInclusive", sinceInclusive, sinceMandatory,
                        "untilInclusive", untilInclusive, untilMandatory);
    }
    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilInclusive(
                    final String sinceParamName, final T sinceInclusive,
                    final String untilParamName, final T untilInclusive) {
        assertSinceInclusiveUntilInclusive(sinceParamName, sinceInclusive, true,
                        untilParamName, untilInclusive, true);
    }
    public static <T extends Temporal & Comparable<T>> void assertSinceInclusiveUntilInclusive(
                    final String sinceParamName, final T sinceInclusive, final boolean sinceMandatory,
                    final String untilParamName, final T untilInclusive, final boolean untilMandatory) {

        final String sinceName = Optional.ofNullable(StringUtils.trimToNull(sinceParamName)).orElse("sinceInclusive");
        final String untilName = Optional.ofNullable(StringUtils.trimToNull(untilParamName)).orElse("untilInclusive");

        if (sinceMandatory) {
            assertParameter(sinceName, sinceInclusive, MANDATORY_PREDICATE);
        }
        if (untilMandatory) {
            assertParameter(untilName, untilInclusive, MANDATORY_PREDICATE);
        }
        if (sinceInclusive != null && untilInclusive != null) {
            assertParameter(() -> String.format("%s (%s) at-or-before %s (%s)", sinceName, sinceInclusive, untilName, untilInclusive),
                            sinceInclusive.compareTo(untilInclusive) <= 0, TRUE_PREDICATE);
        }
    }

    private Validator() {
        // hiding default constructor
    }
}
