head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsNotifyOrderTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �����t�����^�C�v�̒萔��`�N���X(WEB3RlsNotifyOrderTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/09/16 ��(FLJ) �V�K�쐬
 */
package webbroker3.rlsgateway.define;

/**
 * �����t�����^�C�v�̒萔��`�N���X<br>
 *
 * @@author�@@��(FLJ)
 * @@version 1.0
 */
public interface WEB3RlsNotifyOrderTypeDef
{

    /**
     * �A������
     */
    public static final int EXECUTE = 1;

    /**
     * OCO����
     */
    public static final int OCO = 2;

    /**
     * IFD����
     */
    public static final int IFD = 3;

    /**
     * �t�w�l
     */
    public static final int STOP_LlIMIT = 4;

    /**
     * W�w�l
     */
    public static final int W_LlIMIT = 5;

}
@
