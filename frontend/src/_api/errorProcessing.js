export const processErr = err => {
    return `Field [${err.field}] ${err.defaultMessage}\n`
}