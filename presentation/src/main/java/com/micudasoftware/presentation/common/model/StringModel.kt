package com.micudasoftware.presentation.common.model

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource

/**
 * Model for storing information about string type and its value.
 */
sealed interface StringModel {

    /**
     * String stored as string value.
     *
     * @property value value stored as String
     */
    data class String(val value: kotlin.String) : StringModel

    /**
     * String stored as resource.
     *
     * @property id of string resource
     */
    data class Resource(@StringRes val id: Int) : StringModel {

        /**
         * Array of variable number of values that needs to be provided to dynamic strings.
         */
        var params: Array<Any>? = null
            private set

        /**
         * Constructor with variable arguments to support dynamic strings.
         */
        constructor(id: Int, vararg params: Any) : this(id) {
            this.params = arrayOf(*params)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Resource

            return id == other.id &&
                    params.contentEquals(other.params)
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + (params?.contentHashCode() ?: 0)
            return result
        }
    }

    /**
     * String stored as plural resource.
     *
     * @property id of string resource
     * @property count The number used to get the correct string for the current language's plural rules.
     */
    class PluralResource(@PluralsRes val id: Int, val count: Int) : StringModel {

        /**
         * Array of variable number of values that needs to be provided to dynamic strings.
         */
        var params: Array<Any>? = null

        /**
         * Constructor with variable arguments to support dynamic plural strings.
         */
        constructor(id: Int, count: Int, vararg params: Any) : this(id, count) {
            this.params = arrayOf(*params)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as PluralResource
            return id == other.id &&
                    count == other.count &&
                    params.contentEquals(other.params)
        }

        override fun hashCode(): Int {
            var result = id
            result = 31 * result + count
            result = 31 * result + (params?.contentHashCode() ?: 0)
            return result
        }
    }

    /**
     * Function to simplify usage of [StringModel] in compose screens. It either returns string directly
     * (in case of [StringModel.String]) or gets string by calling [stringResource] function with given
     * resource ID and - if it is dynamic string - with varargs (in case of [StringModel.Resource]).
     *
     * @return String value of provided [StringModel]
     */
    @Composable
    fun getString(): kotlin.String {
        return when (this) {
            is Resource -> params?.let {
                stringResource(id, *it)
            } ?: stringResource(id)
            is String -> value
            is PluralResource -> params?.let {
                pluralStringResource(id = id, count = count, *it)
            } ?: pluralStringResource(id = id, count = count)
        }
    }
}
