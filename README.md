# Play! Template XSS Tester
## Intent
The purpose of this app is to test for XSS in Play!'s default escaping templating engine.

## About Play!'s Templating Engine
Play! is a web application framework popular among Scala developers. As with many web frameworks, it includes a templating
engine (Twirl) to assist with writing HTML pages. When it comes to XSS vulnerabilities, Twirl encodes output by default,
 preventing most XSS vulnerabilities in Play! applications. As per Play's documentation:

 ```dtd
By default, dynamic content parts are escaped according to the template type’s (e.g. HTML or XML) rules. If you want to output a raw content fragment, wrap it in the template content type.
```

It is well known that using `@Html` in the template or `as HTML` in the controller will lead to XSS, but I wanted to check
what other XSS avenues existed using the default escaping provided by Twirl. A quick look at Twirl's source code
tells us that this is what they do to prevent XSS:

```scala
 // Using our own algorithm here because commons lang escaping wasn't designed for protecting against XSS, and there
// don't seem to be any other good generic escaping tools out there.
var i = 0
while (i < text.length) {
  text.charAt(i) match {
    case '<' => builder.append("&lt;")
    case '>' => builder.append("&gt;")
    case '"' => builder.append("&quot;")
    case '\'' => builder.append("&#x27;")
    case '&' => builder.append("&amp;")
    case c => builder += c
  }
  i += 1
```

## Results

Certain contexts, such as HTML attribute names, or tag names, are considered almost impossible to correctly encode in all situations,
so it is expected that XSS would be present if the developer renders user input inside them. Other occurrences could be prevented if
Twirl were to use context-aware encoding.

| Context                                  | Vulnerable  |
|------------------------------------------|:-----------:|
| HTML Attribute Name                      | Yes         |
| HTML Comment                             | No          |
| HTML Tag Name                            | Yes         |
| HTML Attribute Value (Single Quoted)     | No          |
| HTML Attribute Value (Unquoted)          | Yes         |
| HTML Attribute Value (Double Quoted)     | No          |
| HTML Element Text                        | No          |
| HTML Link HREF                           | Yes         |
| HTML Link HREF Query Parameter           | No          |
| JS Function Argument                     | Yes         |
| JS Right Hand Value (Double Quoted)      | No          |
| JS Right Hand Value (Single Quoted)      | No          |
| JS Right Hand Value (Unquoted)           | Yes         |
| Style Tag Context                        | No          |
| Style Property Value (Double Quoted)     | No          |
| Style Property Value (Single Quoted)     | No          |
| Style Selector Property Value            | No          |
| Style Selector Property Value (Unquoted) | No          |

## Project Structure
This is a standard Play! app that contains several pages rendering user controlled input under different contexts (e.g. Javascript, HTML attribute). It
also uses different quotations (no quotes, single quote, double quote). There is an `index` page that links to the rest of the test
pages to make it simpler for automated tools to scan the application if desired.

The `views` package contains the different pages each rendering input under a different context. There is only one controller that 
simply renders the associated view.

### How to run it
Inside the project folder, run:

`> sbt run`

The application should start on `http://localhost:9000`.

## Contributions
If you want to add additional contexts to tests, or in any way improve this project, feel free to send a PR or reach out to me
on twitter at [@adrianbravon](https://twitter.com/adrianbravon).
