head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsNotifyStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �u�����敪�v�̒萔��`�N���X(WEB3RlsNotifyStatusDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/09/16 ��(FLJ) �V�K�쐬
 */
package webbroker3.rlsgateway.define;

/**
 * �u�����敪�v�̒萔��`�N���X<br>
 *
 * @@author�@@��(FLJ)
 * @@version 1.0
 */
public interface WEB3RlsNotifyStatusDef
{

    /**
     * ������
     */
    public static final String NOT_DEAL = "0";

    /**
     * ������
     */
    public static final String DEAL = "1";

    /**
     * ���[���G���W����t
     */
    public static final String RLS_ACCEPT = "2";

    /**
     * �v���O�����G���[
     */
    public static final String PROGRAM_ERROR = "8";

    /**
     * �f�[�^�G���[
     */
    public static final String DATA_ERROR = "9";

}
@
