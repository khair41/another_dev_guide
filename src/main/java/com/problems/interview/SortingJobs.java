package com.problems.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingJobs {
    public static void main(String[] args) {
        Job j1 = new Job("J1", 0, 10);
        Job j2 = new Job("J2", 3, 7);
        Job j3 = new Job("J3", 6, 4);
        Job j4 = new Job("J4", 0, 4);

        List<Job> jobs = new ArrayList<>();
        jobs.add(j1);
        jobs.add(j2);
        jobs.add(j3);
        jobs.add(j4);
        // Collections.sort(jobs,
        // Comparator.comparing(Job::getSubmitAt).thenComparing(Job::getDuration));
        System.out.println(jobs);
        helper(jobs, 0);
    }

    static void helper(List<Job> jobs, long time) {
        List<Job> submittedJobs = new ArrayList<>();
        for (Job job : jobs) {
            if (job.submitAt <= time) {
                submittedJobs.add(job);
            }
        }

        Collections.sort(submittedJobs, (j1, j2) -> Long.compare(j1.duration, j2.duration));
        long maxDuration = submittedJobs.get(submittedJobs.size() - 1).duration;
        System.out.println(submittedJobs);
        jobs.removeAll(submittedJobs);
        if (!jobs.isEmpty())
            helper(jobs, time + maxDuration);
    }

    static class Job {
        String name;
        long submitAt;
        long duration;

        public Job(String name, long submitAt, long duration) {
            this.name = name;
            this.submitAt = submitAt;
            this.duration = duration;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getSubmitAt() {
            return submitAt;
        }

        public void setSubmitAt(long submitAt) {
            this.submitAt = submitAt;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "Job{" + "name='" + name + '\'' + ", submitAt=" + submitAt + ", duration="
                    + duration + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Job job = (Job) o;

            if (submitAt != job.submitAt)
                return false;
            if (duration != job.duration)
                return false;
            return name != null ? name.equals(job.name) : job.name == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (int) (submitAt ^ (submitAt >>> 32));
            result = 31 * result + (int) (duration ^ (duration >>> 32));
            return result;
        }
    }
}
