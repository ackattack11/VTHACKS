__author__ = 'Adam'
import warnings
import sys
import os
from metamind.api import ClassificationData, ClassificationModel, set_api_key, general_image_classifier
warnings.filterwarnings("ignore")
url = sys.argv[1]

set_api_key("Xnxh4kNZgcykl9ePz4nfoRV7EVYW5BM9WDQGoMopzVObFJdvzR")

# training_data = ClassificationData(private=True, data_type='image', name='training images')
# training_data.add_samples([
#   ('http://newsimg.bbc.co.uk/media/images/46310000/jpg/_46310103_r850082-andromeda_galaxy_(m31)-spl.jpg', 'galaxy'),
#   ('https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSNB1gxHaGwKNV8r-AvYgST0PiM4t9YXU7e8XRELdTHGx50dqtUMg', 'galaxy'),
#   ('https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT7KaboL9LLsjMTM346fphYW3fufnVN8zMxcG7FhvDahepCET0sXA', 'galaxy'),
#   ('https://maleficusamore.files.wordpress.com/2012/06/sirius20crop1.jpg', 'star'),
#   ('http://aetherforce.com/wp-content/uploads/2014/12/sun_stars_space_light_58237_1920x1180.jpg', 'star'),
#   ('http://cdn.spacetelescope.org/archives/images/publicationjpg/heic1312a.jpg', 'planet'),
#   ('http://orig00.deviantart.net/6290/f/2006/336/b/0/planet_stock_5_by_bareck.jpg', 'planet'),
#   ('https://upload.wikimedia.org/wikipedia/commons/8/85/Venus_globe.jpg', 'planet'),
#   ('https://stenila.files.wordpress.com/2014/08/purchased-elanon.jpg?w=569&h=367', 'star')],
#   input_type='urls')
#
# classifier = ClassificationModel(private=True, name='my classifier')
# classifier.fit(training_data)

print general_image_classifier.predict(url, input_type='urls')
list = [general_image_classifier.predict(url, input_type='urls')]
firstList = list[0]
dict = firstList[0]
print dict['label']

