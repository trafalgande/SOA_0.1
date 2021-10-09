
const paths = require("./paths.json")

const get = (path, token) => {
    fetch(path, intercept('GET', token))
        .then(response => response.json())
        .then(console.log)
}

const post = (path, token, payload) => {
    fetch(path, intercept('POST', token, payload))
        .then(response => response.json())
        .then(console.log)
}

const del = (path, token) => {
    fetch(path, intercept('DELETE', token))
        .then(response => response.json())
        .then(console.log)
}

const patch = (path, token, payload) => {
    fetch(path, intercept('PATCH', token, payload))
        .then(response => response.json())
        .then(console.log)
}


const intercept = (method, token, payload) => {
    let headers = new Headers()
    headers.append("Authorization", `Bearer ${token}`)

    switch (method) {
        case 'DELETE':
        case 'GET': {
            return {
                method: method,
                headers: headers,
                redirect: 'follow'
            };
        }
        case 'PATCH':
        case 'POST': {
            headers.append("Content-Type", "application/json")
            let raw = JSON.stringify(payload)

            return {
                method: method,
                headers: headers,
                body: raw,
                redirect: 'follow'
            };
        }
    }
}

export const getAllMusicBands = (token, options) => {
    const {page, size, sort} = options
    get(paths['ALL_MUSIC_BANDS'], token)
}



