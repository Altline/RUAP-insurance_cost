package altline.insurance

class UnexpectedFormatException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}