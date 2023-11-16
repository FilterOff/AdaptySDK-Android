package com.adapty.models

import com.adapty.internal.domain.models.BackendProduct
import com.adapty.internal.utils.immutableWithInterop
import com.adapty.utils.ImmutableList
import com.adapty.utils.ImmutableMap

/**
 * @property[abTestName] Parent A/B test name.
 * @property[hasViewConfiguration] If `true`, it is possible to fetch the [AdaptyViewConfiguration] object and use it with [AdaptyUI](https://search.maven.org/artifact/io.adapty/android-ui) library.
 * @property[id] An identifier of a paywall, configured in Adapty Dashboard.
 * @property[locale] An identifier of a paywall locale.
 * @property[name] A paywall name.
 * @property[remoteConfig] A custom map configured in Adapty Dashboard for this paywall (same as [remoteConfigString])
 * @property[remoteConfigString] A custom JSON string configured in Adapty Dashboard for this paywall.
 * @property[revision] Current revision (version) of a paywall. Every change within a paywall creates a new revision.
 * @property[variationId] An identifier of a variation, used to attribute purchases to this paywall.
 * @property[vendorProductIds] Array of related products ids.
 */
public class AdaptyPaywall internal constructor(
    public val id: String,
    public val name: String,
    public val abTestName: String,
    public val revision: Int,
    public val variationId: String,
    public val locale: String,
    public val remoteConfigString: String?,
    public val remoteConfig: ImmutableMap<String, Any>?,
    @get:JvmName("hasViewConfiguration")
    public val hasViewConfiguration: Boolean,
    @get:JvmSynthetic internal val products: List<BackendProduct>,
    private val updatedAt: Long,
) {

    public val vendorProductIds: ImmutableList<String> get() =
        products.map { it.vendorProductId }.immutableWithInterop()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdaptyPaywall

        if (id != other.id) return false
        if (name != other.name) return false
        if (abTestName != other.abTestName) return false
        if (revision != other.revision) return false
        if (variationId != other.variationId) return false
        if (vendorProductIds != other.vendorProductIds) return false
        if (locale != other.locale) return false
        if (remoteConfigString != other.remoteConfigString) return false
        if (remoteConfig != other.remoteConfig) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + abTestName.hashCode()
        result = 31 * result + revision
        result = 31 * result + variationId.hashCode()
        result = 31 * result + vendorProductIds.hashCode()
        result = 31 * result + locale.hashCode()
        result = 31 * result + (remoteConfigString?.hashCode() ?: 0)
        result = 31 * result + (remoteConfig?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "AdaptyPaywall(id=$id, name=$name, abTestName=$abTestName, revision=$revision, variationId=$variationId, vendorProductIds=$vendorProductIds, locale=$locale, remoteConfig=$remoteConfig)"
    }
}