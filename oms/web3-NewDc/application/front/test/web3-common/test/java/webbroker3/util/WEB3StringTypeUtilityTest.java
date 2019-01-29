head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3StringTypeUtilityTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������̕�����𔻒f����@@�\Test(WEB3StringTypeUtilityTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/20 ��іQ(���u) �V�K�쐬
*/

package webbroker3.util;

import webbroker3.mock.TestBaseForMock;

public class WEB3StringTypeUtilityTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3StringTypeUtilityTest.class);

    public WEB3StringTypeUtilityTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * isMulti���\�b�h���g�p����1�������S�p�����p���𔻒f���A<BR>
     * �Z�o�����o�C�g����ԋp����B<BR>
     */
    public void testGetFixedByteLengthCase0001()
    {
        final String STR_METHOD_NAME = "testGetFixedByteLengthCase0001()";
        log.entering(STR_METHOD_NAME);

        String l_str = "ABCD�d�e�f�g�`�a";

        try
        {
            int l_intFixedByteLength = WEB3StringTypeUtility.getFixedByteLength(l_str);
         
            assertEquals(16, l_intFixedByteLength);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
