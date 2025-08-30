package com.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark a Problem class that should be excluded
 * from the dynamic discovery process in the ProblemRegistry.
 * <p>
 * This is useful for temporarily disabling problems or for keeping
 * work-in-progress problems from appearing in the main menu.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcludeFromRegistry {
}
