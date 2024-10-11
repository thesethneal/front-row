<template>
  <div class="event-component">
    <h3 id="month">{{ formatEventMonth(event.event.eventTime) || 'No time' }}</h3>
    <h3 id="day">{{ formatEventDay(event.event.eventTime) || 'No time' }}</h3>
    <h4 id="time">{{ formatEventTime(event.event.eventTime) || 'No time' }}</h4>
    <h4 id="location">{{ event.event.eventAddress }}</h4>
    <p id="venue">{{ event.event.eventVenue }}</p>
    <p id="name">{{ event.event.eventName }}</p>
  </div>
</template>

<script>
export default {
  props: {
    event: {
      type: Object,
      required: true
    }
  },
  methods: {
    formatEventMonth(eventTime) {
      return new Date(eventTime).toLocaleString('en-US', {
        month: 'short',
      }).toUpperCase();
    },
    formatEventDay(eventTime) {
      return new Date(eventTime).toLocaleString('en-US', {
        day: '2-digit',
      }).toUpperCase();
    },
    formatEventTime(eventTime) {
      const date = new Date(eventTime);
      const weekday = date.toLocaleString('en-US', {
        weekday: 'short'
      }).toUpperCase();
      const time = date.toLocaleString('en-US', {
        hour: 'numeric',
        minute: '2-digit'
      });
      return `${weekday} | ${time}`;
    }
  }

}
</script>

<style scoped>
.event-component {
  width: 200px;
  height: 200px;
  padding: 1em;
  background-color: white;
  border-radius: 15px;
  margin: 1em;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  justify-content: center;
}

h3 {
  font-size: 2.5em;
  color: #333;
}

h4 {
  font-size: 1em;
  color: #555;
}

p {
  font-size: 0.9em;
  color: #666;
}

/* .event-component:hover {
  transform: scale(1.05);
  transition: transform 0.2s ease;
} */

#month {
  font-weight: bold;
  color: #333;
  font-size: 3em;
}

#day {
  color: #333;
  font-size: 2.8em;
  margin-bottom: 0.2em
}

#time,
#venue,
#location,
#name {
  margin: 0.1em;
}
</style>