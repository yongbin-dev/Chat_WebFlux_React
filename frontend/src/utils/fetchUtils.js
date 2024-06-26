// fetch 함수
export const fetchApi = async (apiUrl, method = 'GET', fetchData, auth = false) => {
  const url = "http://localhost:8080" + apiUrl;

  try {
    if (method === 'GET') {
      const res = await fetch(url, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json;charset=utf-8',
          Authorization: auth ? localStorage.getItem('accessToken') : null,
        },
      });
      return res.json();
    }
    const res = await fetch(url, {
      method,
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
        // Authorization: auth ? localStorage.getItem('accessToken') : null,
      },
      body: JSON.stringify(fetchData),
    });
    return res.json();
  } catch (e) {
    return e;
  }
};