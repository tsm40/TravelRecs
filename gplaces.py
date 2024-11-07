import requests
import pandas as pd
import time

API_KEY = 'AIzaSyA3vnqzplb1E2tyAvBh3TXxICWYHli1lT4'
OUTPUT_CSV = 'hawaii_places.csv'
PLACE_TYPES = ['tourist_attraction', 'restaurant', 'lodging']
LOCATION = '21.289373, -157.917480'  # Hawaii
RADIUS = 50000  # meters, per search
MAX_RESULTS = 100

def get_places_by_type(location, radius, place_type, max_results=MAX_RESULTS):
    url = f"https://maps.googleapis.com/maps/api/place/nearbysearch/json"
    places = []
    params = {
        'location': location,
        'radius': radius,
        'type': place_type,
        'key': API_KEY
    }

    while len(places) < max_results:
        response = requests.get(url, params=params).json()
        results = response.get('results', [])
        places.extend(results)
        if 'next_page_token' in response and len(places) < max_results:
            params['pagetoken'] = response['next_page_token']
            time.sleep(2)
        else:
            break

    return places

def get_place_details(place_id):
    url = f"https://maps.googleapis.com/maps/api/place/details/json"
    params = {
        'place_id': place_id,
        'key': API_KEY,
        'fields': 'name,place_id,formatted_address,address_components,type,geometry,rating,price_level,editorial_summary'
    }
    response = requests.get(url, params=params)
    return response.json().get('result', {})

def get_address_component(components, component_type):
    for component in components:
        if component_type in component['types']:
            return component['long_name']
    return None

data = []
for place_type in PLACE_TYPES:
    print(f"Fetching data for place type: {place_type}")
    places = get_places_by_type(LOCATION, RADIUS, place_type)
    for place in places:
        place_id = place['place_id']
        details = get_place_details(place_id)
        data.append({
            'name': details.get('name', ''),
            'place_id': details.get('place_id', ''),
            'address': details.get('formatted_address', ''),
            'description': details['editorial_summary']['overview'],
            'type': place_type,
            'rating': details.get('rating', ''),
            'price_level': details.get('price_level', ''),
            'latitude': details['geometry']['location']['lat'],
            'longitude': details['geometry']['location']['lng'],
            'city': get_address_component(details.get('address_components', []), 'locality'),
            'state': get_address_component(details.get('address_components', []), 'administrative_area_level_1'),
            'country': get_address_component(details.get('address_components', []), 'country')
        })
        time.sleep(1)  # rate limit

df = pd.DataFrame(data)
df.to_csv(OUTPUT_CSV, index=False)
print(f"Data saved to {OUTPUT_CSV}")
