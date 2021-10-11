import {dispatch} from "./pathDispatcher";
import {notify} from "../containers/notificationContainer/notifications";
import {withDefaultOptions} from "./_options";


const get = async (path, token) => {
    return await fetch(path, intercept('GET', token))
        .catch(err => notify('Internal error', 'error'))
}

const post = async (path, token, payload) => {
    return await fetch(path, intercept('POST', token, payload))
        .catch(err => notify('Internal error', 'error'))

}

const del = async (path, token) => {
    return await fetch(path, intercept('DELETE', token))
        .catch(err => notify('Internal error', 'error'))

}

const put = async (path, token, payload) => {
    return await fetch(path, intercept('PUT', token, payload))
        .catch(err => notify('Internal error', 'error'))

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
        case 'PUT':
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
    const url = dispatch('MUSIC_BANDS', options)
    return get(url, token)
}

export const postNewMusicBand = (token, options,
                                 {
                                     name, x, y,
                                     numOfParticipants,
                                     description,
                                     genre,
                                     sales
                                 }) => {
    const url = dispatch('MUSIC_BAND', options)
    return post(url, token, {
        "name": name,
        "coordinates": {
            "x": x,
            "y": y
        },
        "numberOfParticipants": numOfParticipants,
        "description": description,
        "genre": genre,
        "label": {
            "sales": sales
        }
    })
}

export const updateMusicBand = (token, options,
                                {
                                    id, name, x, y,
                                    numOfParticipants,
                                    description,
                                    genre,
                                    sales
                                }) => {
    const url = dispatch('MUSIC_BAND_BY_ID', options, id)
    return put(url, token, {
        "name": name,
        "coordinates": {
            "x": x,
            "y": y
        },
        "numberOfParticipants": numOfParticipants,
        "description": description,
        "genre": genre,
        "label": {
            "sales": sales
        }
    })
}

export const deleteMusicBand = (token, options, id) => {
    const url = dispatch('MUSIC_BAND_BY_ID', options, id)
    return del(url, token)
}

export const countMusicBandsByNumberOfParticipantsLessThen = (token, options, value) => {
    const url = dispatch('MUSIC_BANDS_COUNT', withDefaultOptions(`by-number-of-participants=${value}`))
    return get(url, token)
}



