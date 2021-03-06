package src.Clustering;

import src.Index.Cluster;
import src.Index.ClusteringInvList;
import src.Index.ClusteringMatrix;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by amaliujia on 15-1-27.
 */
public class WordToCluster extends ObjectToCluster {

    public WordToCluster(int vectorSpaceSize){
        linkage = new TreeMap<Integer, Double>();
        for(int i = 0; i < vectorSpaceSize; i++){
            linkage.put(i, -1.0);
        }
    }

    public void updateLinkage(ClusteringMatrix matrix, List<Cluster> clusters){
        for (Cluster c : clusters){
            int size = c.clusterSize();
            ClusteringInvList invList;
            for(int i = 0; i < size; i++){
                invList = c.getVec(i);
                if(!linkage.containsKey(invList.getInvlistID())) {
                    System.err.println("no inverted list id");
                    continue;
                }
                int invlistid = invList.getInvlistID();
                double cosine =  BipartiteClustering.CosineSimilarity(invList,
                        matrix.getColumnVector(invList.getInvlistID()));
                linkage.put(invList.getInvlistID(), cosine);
            }
        }
    }

}
