async function fetchCurrentUser() {
    try {
      const response = await fetch('/api/auth/me', {
        credentials: 'include' // Important for sending cookies
      });
      
      if (!response.ok) {
        if (response.status === 401) {
          window.location.href = '/login'; // Redirect if unauthorized
          return;
        }
        throw new Error('Failed to fetch user data');
      }
      
      const user = await response.json();
      document.getElementById("userNameSpan").innerText = user.username;
      console.log(user)
    } catch (error) {
      console.error('Error fetching user:', error);
      return null;
    }
}

  function getAllSliderValues() {
    const values = {};
    
    document.querySelectorAll('.slider-container').forEach(container => {
      const label = container.querySelector('.slider-label')?.innerText.toLowerCase();
      const activeOption = container.querySelector('.slider-option.active');
      if (label && activeOption) {
        values[label] = activeOption.dataset.value;
      }
    });
    console.log(values)
    return values;
  }
  
  //Create match function
  async function createMatch() {
    language = getAllSliderValues().language
    difficulty = getAllSliderValues().difficulty
    console.log("labuage " + language)
    console.log("diffuty " + difficulty)
    try {
      const response = await fetch('/api/match/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          difficulty: difficulty,
          programmingLanguage: language
        }),
        credentials: 'include'
      });
      
      if (!response.ok) throw new Error('Failed to create match');
      const matchId = await response.json();
      window.location.href = `WaitingRoom.html?matchId=${matchId}`;
    } catch (error) {
      console.error('Error creating match:', error);
      throw error;
    }
  }
  
fetchCurrentUser()