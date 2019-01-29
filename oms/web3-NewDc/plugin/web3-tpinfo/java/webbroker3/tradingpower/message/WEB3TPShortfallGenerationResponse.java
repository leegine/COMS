head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s���������󋵉�ʕ\�����X�|���X(WEB3TPShortfallGenerationResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/10 �����i���u�j�V�K�쐬 ���f��No.312
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�s���������󋵉�ʕ\�����X�|���X) <BR>
 * �s���������󋵉�ʕ\�����X�|���X�N���X�B<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3TPShortfallGenerationResponse extends WEB3GenResponse
{

    /**
     * (PTYPE) <BR>
     */
    public static final String PTYPE = "shortfall_generation";

    /**
     * (SerialVersionUID) <BR>
     */
    public static final long serialVersionUID = 200810101022L;

    /**
     * (�s����������)<BR>
     * �s����������<BR>
     * <BR>
     * 0 : �s����������<BR>
     * 1 : �s��������<�����ڋq><BR>
     * 2 : �s��������<�M�p�ڋq><BR>
     */
    public String shortfallGenerationStateDiv;

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * �ۏ؋������U�֌㔻��t���O<BR>
     * <BR>
     * false : �ۏ؋������U�֑O<BR>
     * true : �ۏ؋������U�֌�<BR>
     */
    public boolean autoTransferAfterJudgeFlag;

    /**
     * (�����x�������t���O)<BR>
     * �����x�������t���O<BR>
     * <BR>
     * false : �����x������<BR>
     * true : �����x���L��<BR>
     */
    public boolean payDelayGenerationFlag;

    /**
     * (�s�����������)<BR>
     * �s�����������<BR>
     */
    public WEB3TPShortfallGenerationInfo shortfallGenerationInfo;

    /**
     * (�R���X�g���N�^) <BR>
     */
    public WEB3TPShortfallGenerationResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3TPShortfallGenerationResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
