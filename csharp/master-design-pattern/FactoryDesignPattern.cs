using System;
namespace master_design_pattern
{
    public abstract class Product
    {
        public String Name;
        public long Price;

        public abstract long Order(long amount);
    }

    public class PricedProduct : Product
    {
        public override long Order(long amount)
        {
            return Price * amount;
        }
    }

    public class FreeProduct : Product
    {
        public override long Order(long amount)
        {
            return 0;
        }
    }

    public class Factory
    {
        public Product NewProduct(String name, long price)
        {
            Product product;
            if (price == 0)
            {
                product = new FreeProduct();
                product.Name = name + " FREE";
            }
            else
            {
                product = new PricedProduct();
                product.Name = name;
                product.Price = price;
            }
            return product;
        }
    }
}
