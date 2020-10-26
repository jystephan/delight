package co.datamechanics.listener.dto

import org.json4s.JsonAST.JValue
import org.json4s.JsonDSL._


case class DmAppId(dmAppId: String) {
  def toJson: JValue = {
    "dmAppId" -> dmAppId
  }
}
