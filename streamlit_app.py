import streamlit as st
import numpy as np
import pickle
model = pickle.load(open('C:/Users/91983/mlapp/model.pkl', 'rb'))
st.title('Placement Predictor')
st.write('Enter the details below to predict your placement status.')
cgpa = st.text_input('CGPA', '0')
iq = st.text_input('IQ', '0')
profile_score = st.text_input('Profile Score', '0')
if st.button('Predict'):
    input_query = np.array([[cgpa, iq, profile_score]])
    result = model.predict(input_query)[0]
    st.write(f'Placement: {result}')
