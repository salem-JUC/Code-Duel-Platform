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

  //Create match function
  async function createMatch() {
    language = "Java"
    difficulty = "Easy"
    try {
      const response = await fetch('/api/match/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          difficulty: "Easy",
          programmingLanguage: "Java"
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