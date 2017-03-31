package au.com.dius.pactworkshop.springbootprovider

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@RestController
class RootController {

  @RequestMapping("/provider.json")
  Map providerJson(@RequestParam(required = false) String validDate) {
    if (validDate) {
      try {
        def valid_time = LocalDateTime.parse(validDate)
        [
          test: 'NO',
          validDate: OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")),
          count: 1000
        ]
      } catch (e) {
        throw new InvalidQueryParameterException("'$validDate' is not a date", e)
      }
    } else {
      throw new QueryParameterRequiredException('validDate is required')
    }
  }

}
