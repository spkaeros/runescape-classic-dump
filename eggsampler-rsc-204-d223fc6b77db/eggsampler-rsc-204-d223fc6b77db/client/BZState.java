class BZState {

    BZState() {
        unzftab = new int[256];
        cftab = new int[257];
        //anIntArray488 = new int[257]; // unused
        inUse = new boolean[256];
        inUse_16 = new boolean[16];
        setToUnseq = new byte[256];
        mtfa = new byte[4096];
        mtfbase = new int[16];
        selector = new byte[18002];
        selectorMtf = new byte[18002];
        len = new byte[6][258];
        limit = new int[6][258];
        base = new int[6][258];
        perm = new int[6][258];
        minLens = new int[6];
    }

    /* unused
    final int anInt456 = 4096; // MTFASIZE ?
    final int anInt457 = 16; // MTFLSIZE
    final int anInt458 = 258;
    final int anInt459 = 23;
    final int anInt460 = 1;
    final int anInt461 = 6; // BZNGROUPS ?
    final int anInt462 = 50; // BZGSIZE ?
    final int anInt463 = 4;
    final int anInt464 = 18002; */
    byte input[];
    int nextIn;
    int availIn;
    int totalInLo32;
    int totalInHi32;
    byte output[];
    int availOut;
    int decompressedSize;
    int totalOutLo32;
    int totalOutHi32;
    byte stateOutCh;
    int stateOutLen;
    boolean blockRandomised;
    int bsBuff;
    int bsLive;
    int blocksize100k;
    int blockNo;
    int origPtr;
    int tpos;
    int k0;
    int unzftab[];
    int nblockUsed;
    int cftab[];
    //int anIntArray488[]; // unused
    public int tt[]; // this was static, wonder why?
    int nInUse;
    boolean inUse[];
    boolean inUse_16[];
    byte setToUnseq[];
    byte mtfa[];
    int mtfbase[];
    byte selector[];
    byte selectorMtf[];
    byte len[][];
    int limit[][];
    int base[][];
    int perm[][];
    int minLens[];
    int saveNblock;
}
