head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �u�����敪�v�̒萔��`�N���X(WEB3TPStatusDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/02/02 kikuchi(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.define;

/**
 * �u�����敪�v�̒萔��`�N���X<br>
 *
 * @@author kikuchi
 * @@version 1.0
 */
public interface WEB3TPStatusDef
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
     * �v���O�����G���[
     */
    public static final String PROGRAM_ERROR = "8";

    /**
     * �f�[�^�G���[
     */
    public static final String DATA_ERROR = "9";

}
@
