head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginClosingStatusTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������Ϗ�ԋ敪(WEB3MarginClosingStatusTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 ������(���u) �V�K�쐬
*/
package webbroker3.equity.define;

/**
 * ���Ϗ�ԋ敪
 *                                                                     
 * @@author wu-yanfei
 * @@version 1.0
 */
public interface WEB3MarginClosingStatusTypeDef
{
    /**
     * 0�F���ύ�
     */
    public static final String SETTLEMENT_END = "0";
    
    /**
     * 1�F������
     */
    public static final String HAVE_NOT_SETTLED = "1";

    /**
     * 2�F���ϒ�
     */
    public static final String SETTLING = "2";
}
@
