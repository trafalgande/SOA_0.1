import {isNil} from "lodash";

const paths = require("./paths.json")
const ROOT = 'http://localhost:8080'

export const dispatch = (key, options, id) => {
    let currentPath = paths[key]
    if (currentPath.includes('{')) {
        currentPath = currentPath.replace('{id}', id)
    }
    return assemblePath(currentPath, options)
}

const assemblePath = (path, options) => {
    return `${ROOT}${!isNil(options) ? applyOptions(path, options) : path}`
}

const applyOptions = (path, options) => {
    const {page, size, sort, filter} = options
    return `${path}?${!isNil(filter) ? `${filter}` : ''}${!isNil(page) ? `&page=${page}` : ''}${!isNil(page) ? `&size=${size}` : ''}${!isNil(page) ? `&sort=${sort}` : ''}`
}