head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExpirationStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪�萔��`�C���^�t�F�C�X(WWEB3FeqExpirationStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 �Г� (���u) �V�K�쐬
*/
package webbroker3.feq.define;

/**
 * �����敪�萔��`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqExpirationStatusDef
{
    /**
     * 0:�����Ȃ�
     */
    public final static String EXPIRATION_TYPE_NOT_PROMISE = "0";
    
    /**
     * 1:�ꕔ����
     */
    public final static String EXPIRATION_TYPE_ONE_COMPLETE = "1";
    
    /**
     * 2:�S������
     */
    public final static String EXPIRATION_TYPE_ALL_COMPLETE = "2";
}
@
