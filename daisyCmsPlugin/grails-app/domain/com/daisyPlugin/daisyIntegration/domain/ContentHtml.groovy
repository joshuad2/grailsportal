package com.daisyPlugin.daisyIntegration.domain


class ContentHtml {
    String htmlText
    Content content
    static constraints = {
      content(blank:false, nullable:false)
      htmlText(size:1..4000, blank:false, nullable:false)
    }
}
