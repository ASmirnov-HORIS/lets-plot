# Datalore Plot for Python

<table>
    <tr>
        <td>Latest Release</td>
        <td>
            <a href="https://pypi.org/project/datalore-plot/"/>
            <img src="https://badge.fury.io/py/datalore-plot.svg"/>
        </td>
    </tr>
    <tr>
        <td>License</td>
        <td>
            <a href="https://opensource.org/licenses/MIT"/>
            <img src="https://img.shields.io/badge/License-MIT-yellow.svg"/>
        </td>
    </tr>
    <tr>
        <td>OS</td>
        <td>Linux, MacOS</td>
    </tr>
    <tr>
        <td>Python versions</td>
        <td>3.7, 3.8</td>
    </tr>
</table>

### Implementation Overview

The Datalore Plot python extension includes native backend and a Python API, which was mostly based on the [`ggplot2`](https://ggplot2.tidyverse.org/) package well-known to data scientists who use R.

R `ggplot2` has extensive documentation and a multitude of examples and therefore is an excellent resource for those who want to learn the grammar of graphics. 

Note that the Python API being very similar yet is different in detail from R. Although we have not implemented the entire ggplot2 API in our Python package, we have added a few [new functions](#unfamiliar_functions_used_in_the_examples) and [built-in sampling](#sampling) to our Python API.

You can try the Datalore Plot library in [Datalore](https://datalore.io). Datalore Plot is available in Datalore out-of-the-box and is almost identical to the one we ship as PyPI package. This is because Datalore Plot is an offshoot of the Datalore project from which it was extracted to a separate plotting library.

One important difference is that the python package in Datalore is named **datalore.plot** and the package you install from PyPI has name **datalore_plot**.

The advantage of [Datalore](https://datalore.io) as a learning tool in comparison to Jupyther is that it is equipped with very friendly Python editor which comes with auto-completion, intentions, and other useful coding assistance features.


### Installation

To install the Datalore Plot library, run the following command:
```shell script
pip install datalore-plot
```

### Quickstart in Jupyter

To evaluate the plotting capabilities of Datalore Plot, add the following code to a Jupyter notebook:
```python
import numpy as np
from datalore_plot import *

np.random.seed(12)
data = dict(
    cond=np.repeat(['A','B'], 200),
    rating=np.concatenate((np.random.normal(0, 1, 200), np.random.normal(1, 1.5, 200)))
)

ggplot(data, aes(x='rating', fill='cond')) + ggsize(500, 250) \
+ geom_density(color='dark_green', alpha=.7) + scale_fill_brewer(type='seq') \
+ theme(axis_line_y='blank')
````

<img src="https://github.com/alshan/jupyter-examples/blob/master/images/quickstart.png" alt="Couldn't load quickstart.png" width="505" height="260"/>


### Examples

Try the following examples to study more features of the Datalore Plot library.

TBD: replace temporary URLs ---> permanent 

Quickstart in Jupyter: [quickstart.ipynb](https://nbviewer.jupyter.org/github/alshan/jupyter-examples/blob/master/notebooks/quickstart.ipynb)

Histogram, density plot, box plot and facets:
[distributions.ipynb](https://nbviewer.jupyter.org/github/alshan/jupyter-examples/blob/master/notebooks/distributions.ipynb) 

Error-bars, points, lines, bars, dodge position:
[error_bars.ipynb](https://nbviewer.jupyter.org/github/alshan/jupyter-examples/blob/master/notebooks/error_bars.ipynb)
 
Points, point shapes, linear regression, jitter position:
[scatter_plot.ipynb](https://nbviewer.jupyter.org/github/alshan/jupyter-examples/blob/master/notebooks/scatter_plot.ipynb)
 
Points, density2d, polygons, density2df:
[density_2d.ipynb](https://nbviewer.jupyter.org/github/alshan/jupyter-examples/blob/master/notebooks/density_2d.ipynb)
 
Tiles, contours, polygons, contourf:
[contours.ipynb](https://nbviewer.jupyter.org/github/alshan/jupyter-examples/blob/master/notebooks/contours.ipynb)
 
Various presentation options:
[legend_and_axis.ipynb](https://nbviewer.jupyter.org/github/alshan/jupyter-examples/blob/master/notebooks/legend_and_axis.ipynb)
 

### Unfamiliar functions used in the examples

* `ggsize()` - sets size of the plot. Used in many examples starting from `quickstart`
* `geom_density2df()` - fills space between equal density lines on 2D density plot. Similar to `geom_density2d` but supports `fill` aesthetic.

    Example: [density_2d.ipynb](https://nbviewer.jupyter.org/github/JetBrains/datalore-plot/blob/master/docs/examples/jupyter-notebooks/density_2d.ipynb) 

* `geom_contourf()` - fills space between lines of equal level of bivariate function. Similar to `geom_contour` but supports `fill` aesthetic.

    Example: [contours.ipynb](https://nbviewer.jupyter.org/github/JetBrains/datalore-plot/blob/master/docs/examples/jupyter-notebooks/contours.ipynb) 


### Sampling 

Sampling is a special technique of data transformation, which helps dealing with large datasets and overplotting.

[Learn more](docs/sampling_python.md) about sampling in Datalore Plot. 


### License

Code and documentation released under the [MIT license](https://github.com/JetBrains/datalore-plot/blob/master/LICENSE).
Copyright 2019, JetBrains s.r.o.
    



