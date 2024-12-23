package com.rikezero.dektek.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Conditionally apply one modifier block if [condition] is true, or another if false.
 * Both [ifTrue] and [ifFalse] are optional.
 *
 * By default, the extra modifiers are appended *after* the existing chain.
 * Set [applyBefore] to `true` if you want your [ifTrue] or [ifFalse] modifiers
 * to appear *before* the existing modifiers in the chain.
 *
 * Example usage:
 * ```
 * Modifier.thenIf(
 *     condition = isEnabled,
 *     applyBefore = false,
 *     ifTrue = { background(Color.Green) },
 *     ifFalse = { background(Color.Red) }
 * )
 * ```
 */
@Composable
fun Modifier.thenIf(
    condition: Boolean,
    applyBefore: Boolean = false,
    ifTrue: @Composable (Modifier.() -> Modifier)? = null,
    ifFalse: @Composable (Modifier.() -> Modifier)? = null
): Modifier {
    return when {
        condition && ifTrue != null ->
            if (applyBefore) {
                // Put the new modifiers before the existing chain
                ifTrue(Modifier).then(this)
            } else {
                // Append the new modifiers after the existing chain
                this.then(ifTrue(Modifier))
            }

        !condition && ifFalse != null ->
            if (applyBefore) {
                ifFalse(Modifier).then(this)
            } else {
                this.then(ifFalse(Modifier))
            }

        else -> this
    }
}