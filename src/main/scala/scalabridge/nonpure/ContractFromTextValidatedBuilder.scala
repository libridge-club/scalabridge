package scalabridge.nonpure

import scalabridge.Contract
import scalabridge.VulnerabilityStatus

object ContractFromTextValidatedBuilder {

  def build(text: String, vulnerabilityStatus: VulnerabilityStatus): Contract = {
    val validContractEither = ValidatedContractFromText(text, vulnerabilityStatus).getValid()
    validContractEither match
      case Left(throwables) => throw throwables.head
      case Right(contract)  => contract
  }

}
