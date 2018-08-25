package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def htmlAttrName(payload: String) = Action {
    Ok(views.html.unsafe.html_attr_name(payload))
  }

  def htmlAttrVal(payload: String) = Action {
    Ok(views.html.html_attr_val(payload))
  }

  def htmlAttrUnquotVal(payload: String) = Action {
    Ok(views.html.html_attr_unquot_val(payload))
  }

  def htmlAttrSingleQuotVal(payload: String) = Action {
    Ok(views.html.html_attr_single_quot_val(payload))
  }

  def htmlEl(payload: String) = Action {
    Ok(views.html.html_el(payload))
  }

  def htmlComment(payload: String) = Action {
    Ok(views.html.unsafe.html_comment(payload))
  }

  def htmlHref(payload: String) = Action {
    Ok(views.html.html_href(payload))
  }

  def htmlHrefParam(payload: String) = Action {
    Ok(views.html.html_href_param(payload))
  }

  def htmlTagName(payload: String) = Action {
    Ok(views.html.unsafe.html_tag_name(payload))
  }

  def jsQuotedVal(payload: String) = Action {
    Ok(views.html.js_quoted_val(payload))
  }

  def jsUnquotedVal(payload: String) = Action {
    Ok(views.html.js_unquoted_val(payload))
  }

  def jsSingleQuotVal(payload: String) = Action {
    Ok(views.html.js_single_quot_val(payload))
  }

  def jsFuncArg(payload: String) = Action {
    Ok(views.html.js_func_arg(payload))
  }

  def stylePropValue(payload: String) = Action {
    Ok(views.html.style_prop_value(payload))
  }

  def styleSingleQuotPropValue(payload: String) = Action {
    Ok(views.html.style_single_quot_prop_value(payload))
  }

  def styleUnquotSelPropVal(payload: String) = Action {
    Ok(views.html.style_unquot_sel_prop_val(payload))
  }

  def styleSelPropVal(payload: String) = Action {
    Ok(views.html.style_sel_prop_val(payload))
  }

  def styleContext(payload: String) = Action {
    Ok(views.html.unsafe.style_context(payload))
  }
}
