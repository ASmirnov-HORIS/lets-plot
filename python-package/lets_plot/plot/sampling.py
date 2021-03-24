#
# Copyright (c) 2019. JetBrains s.r.o.
# Use of this source code is governed by the MIT license that can be found in the LICENSE file.
#
from .core import FeatureSpec

__all__ = ['sampling_random',
           'sampling_random_stratified',
           'sampling_pick',
           'sampling_systematic',
           'sampling_group_random',
           'sampling_group_systematic',
           'sampling_vertex_vw',
           'sampling_vertex_dp']


def sampling_random(n, seed=None):
    """
    Return a subset of randomly selected items.

    Parameters
    ----------
    n : int
        Number of items to return.
    seed : int
        Number used to initialize a pseudo random number generator.

    Returns
    -------
    Series or `DataFrame`
        A new object of same type as caller containing n items randomly sampled from the caller object.

    Examples
    --------
    .. jupyter-execute::
        :linenos:
        :emphasize-lines: 10

        import numpy as np
        from lets_plot import *
        LetsPlot.setup_html()
        np.random.seed(27)
        n = 15000
        x, y = np.random.multivariate_normal(mean=[0,0],
                                             cov=[[.9, -.6], [-.6, .9]],
                                             size=n).T
        ggplot({'x': x, 'y': y}, aes(x='x', y='y')) + \\
            geom_point(sampling=sampling_random(1000, 35))

    """
    return _sampling('random', n=n, seed=seed)


def sampling_random_stratified(n, seed=None, min_subsample=None):
    """
    Randomly sample from each stratum (subgroup).

    Parameters
    ----------
    n : int
        Number of items to return.
    seed : int
        Number used to initialize a pseudo random number generator.
    min_subsample: int
        Minimal number of items in sub sample.

    Returns
    -------
    Series or `DataFrame`
        A new object of same type as caller containing n items sampled.

    Examples
    --------
    .. jupyter-execute::
        :linenos:
        :emphasize-lines: 10

        import numpy as np
        from lets_plot import *
        LetsPlot.setup_html()
        np.random.seed(27)
        data = dict(
            x = np.random.normal(0, 1, 1100),
            y = np.random.normal(0, 1, 1100),
            cond = ['1' for i in range(100)] + ['2' for i in range(1000)])
        ggplot(data, aes('x', 'y', color='cond')) + \\
            geom_point(sampling=sampling_random_stratified(50, min_subsample=10))

    """
    return _sampling('random_stratified', n=n, seed=seed, min_subsample=min_subsample)


def sampling_pick(n):
    return _sampling('pick', n=n)


def sampling_systematic(n):
    """
    Return a subset where items are selected at a regular interval.

    Parameters
    ----------
    n : int
        Number of items to return.

    Returns
    -------
    Series or `DataFrame`
        A new object of same type as caller containing n items sampled.

    Examples
    --------
    .. jupyter-execute::
        :linenos:
        :emphasize-lines: 9

        import numpy as np
        from lets_plot import *
        LetsPlot.setup_html()
        n = 1000
        x = np.arange(n)
        np.random.seed(12)
        y = np.random.normal(0, 1, n)
        ggplot({'x': x, 'y': y}, aes(x='x', y='y')) + \\
            geom_line(sampling=sampling_systematic(50))

    """

    return _sampling('systematic', n=n)


def sampling_group_systematic(n):
    return _sampling('group_systematic', n=n)


def sampling_group_random(n, seed=None):
    return _sampling('group_random', n=n, seed=seed)


def sampling_vertex_vw(n):
    """
    Simplify a polyline using the Visvalingam-Whyatt algorithm.

    Parameters
    ----------
    n : int
        Number of items to return.

    Returns
    -------
    Series or `DataFrame`
        A new object of same type as caller containing n items sampled.

    Note
    ----
    Vertex sampling is designed for polygon simplification.

    Examples
    --------
    .. jupyter-execute::
        :linenos:
        :emphasize-lines: 17

        import numpy as np
        from scipy.stats import multivariate_normal
        from lets_plot import *
        LetsPlot.setup_html()
        np.random.seed(42)
        n = 300
        x = np.linspace(-1, 1, n)
        y = np.linspace(-1, 1, n)
        X, Y = np.meshgrid(x, y)
        mean = np.zeros(2)
        cov = [[1, .5],
               [.5, 1]]
        rv = multivariate_normal(mean, cov)
        Z = rv.pdf(np.dstack((X, Y)))
        data = {'x': X.flatten(), 'y': Y.flatten(), 'z': Z.flatten()}
        ggplot(data, aes(x='x', y='y', z='z')) + \\
            geom_contour(sampling=sampling_vertex_vw(150))
    """
    return _sampling('vertex_vw', n=n)


def sampling_vertex_dp(n):
    """
    Simplify a polyline using the Douglas-Peucker algorithm.

    Parameters
    ----------
    n : int
        Number of items to return.

    Returns
    -------
    Series or `DataFrame`
        A new object of same type as caller containing n items sampled.

    Note
    ----
    Vertex sampling is designed for polygon simplification.

    Examples
    --------
    .. jupyter-execute::
        :linenos:
        :emphasize-lines: 17

        import numpy as np
        from scipy.stats import multivariate_normal
        from lets_plot import *
        LetsPlot.setup_html()
        np.random.seed(42)
        n = 300
        x = np.linspace(-1, 1, n)
        y = np.linspace(-1, 1, n)
        X, Y = np.meshgrid(x, y)
        mean = np.zeros(2)
        cov = [[1, .5],
               [.5, 1]]
        rv = multivariate_normal(mean, cov)
        Z = rv.pdf(np.dstack((X, Y)))
        data = {'x': X.flatten(), 'y': Y.flatten(), 'z': Z.flatten()}
        ggplot(data, aes(x='x', y='y', z='z')) + \\
            geom_contour(sampling=sampling_vertex_dp(150))
    """
    return _sampling('vertex_dp', n=n)


def _sampling(name, **kwargs):
    return FeatureSpec('sampling', name, **kwargs)
