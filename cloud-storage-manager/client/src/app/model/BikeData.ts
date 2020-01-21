export interface BikeData {
  trip_id: string;
  year: number;
  month: number;
  week: number;
  day: number;
  hour: number;
  usertype: string;
  gender: string;
  starttime: string;
  stoptime: string;
  tripduration: number;
  temperature: number;
  events: string;
  from_station_id: string;
  from_station_name: string;
  latitude_start: number;
  longitude_start: number;
  dpcapacity_start: number;
  to_station_id: string;
  to_station_name: string;
  latitude_end: number;
  longitude_end: number;
  dpcapacity_end: number;

}
