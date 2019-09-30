import pytest

import datalore.plot as gg


class TestWithListArgs:
    result_empty = {'x': None, 'y': None}
    result_xy = {'x': 'xVar', 'y': 'yVar'}

    @pytest.mark.parametrize('args,expected', [
        ([], result_empty),
        (['xVar', 'yVar'], result_xy),
    ])
    def test_aes(self, args, expected):
        spec = gg.aes(*args)
        assert spec.as_dict() == expected


class TestWithDictArgs:
    result_kwargs = {'x': 'xVar', 'y': 'yVar', 'size': 'sizeVar'}

    @pytest.mark.parametrize('args,expected', [
        (result_kwargs, result_kwargs),
    ])
    def test_aes(self, args, expected):
        spec = gg.aes(**args)
        assert spec.as_dict() == expected
