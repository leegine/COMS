head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExpirationStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪 �萔��`�C���^�t�F�C�X(WEB3EquityExpirationStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 �������F(SRA) �V�K�쐬
*/

package webbroker3.equity.define;

/**
 * �����敪�@@�萔��`�C���^�t�F�C�X
 * 
 * @@author �������F
 * @@version 1.0
 */
public interface WEB3EquityExpirationStatusDef
{
    /**
     * �����敪:
     * 0:�����Ȃ�
     */
    public final static String EXPIRATION_TYPE_NOT_PROMISE = "0";
    
    /**
     * �����敪:
     * 1:�ꕔ����
     */
    public final static String EXPIRATION_TYPE_ONE_COMPLETE = "1";
    
    /**
     * �����敪:
     * 2:�S������
     */
    public final static String EXPIRATION_TYPE_ALL_COMPLETE = "2";
}
@
