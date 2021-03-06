package characterscounter;

import java.io.File;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.IntStream;

/**
 * The main entry for running the program
 * 
 * @author Malak
 *
 */
public class CharactersCounter {

	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			throw new IllegalArgumentException("Please provide a path for the folder");
		}

		ForkJoinPool forkJoinPool = new ForkJoinPool();

		FolderSearchTask folder = new FolderSearchTask(new File(args[0]));

		AtomicIntegerArray results = forkJoinPool.invoke(folder);

		forkJoinPool.shutdown();

		// print results
		IntStream.range(0, 26).forEach(index -> System.out.println((char) (index + 97) + "\t" + results.get(index)));

	}
}
