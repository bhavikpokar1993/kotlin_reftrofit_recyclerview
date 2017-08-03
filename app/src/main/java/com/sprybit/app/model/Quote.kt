package com.sprybit.app

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Quote {

    @SerializedName("quote_id")
    @Expose
    var quoteId: String? = null
    @SerializedName("quote_text")
    @Expose
    var quoteText: String? = null
    @SerializedName("quote_date")
    @Expose
    var quoteDate: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null

}