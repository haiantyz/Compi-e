import numpy as np
import pylab as pl

data = np.loadtxt('/Users/tyz/IdeaProjects/CompilePra/src/graphdata')
pl.plot(data[:,0],data[:,1],'ro')
pl.xlabel('x')
pl.ylabel('y')
pl.show()
