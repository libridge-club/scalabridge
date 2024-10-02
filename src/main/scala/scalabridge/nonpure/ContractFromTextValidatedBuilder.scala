package scalabridge.nonpure

import scalabridge.Contract

object ContractFromTextValidatedBuilder {

  def build(text: String): Contract = {
    val validContractEither = ValidatedContractFromText(text).getValid()
    validContractEither match
      case Left(throwables) => throw throwables.head
      case Right(contract)  => contract
  }

}
