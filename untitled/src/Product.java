//additional logical implementation of a dependency inversion.
interface Product {
    void seeDistributor();
}

class Book implements Product {
    @Override
    public void seeDistributor() {

    }
}

class Figurine implements Product {
    @Override
    public void seeDistributor() {

    }
}

class Shelf {

    Product product;

    void addProduct(Product product) {
    }
}