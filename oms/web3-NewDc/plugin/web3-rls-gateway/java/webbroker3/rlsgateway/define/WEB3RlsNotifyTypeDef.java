head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsNotifyTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �u�ʒm�o�H�敪�v�̒萔��`�N���X(WEB3RlsNotifyTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/09/16 ��(FLJ) �V�K�쐬
 */
package webbroker3.rlsgateway.define;

/**
 * �u�ʒm�o�H�敪�v�̒萔��`�N���X<br>
 *
 * @@author�@@��(FLJ)
 * @@version 1.0
 */
public interface WEB3RlsNotifyTypeDef
{

    /**
     * ���[���G���W���iDefault�j
     */
    public static final String RLS = "0";

    /**
     * �蓮����(�ڋq)
     */
    public static final String MANUAL_USER = "1";

    /**
     * �蓮����(�I�y���[�^)
     */
    public static final String MANUAL_OPERATOR = "2";

    /**
     * �蓮����(�Ǘ���)
     */
    public static final String MANUAL_ADMIN = "3";

    /**
     * �ʒm�o�H�F�Ď�(�I�����C��)
     */
    public static final String OBSERVER_ONLINE = "4";

}
@
