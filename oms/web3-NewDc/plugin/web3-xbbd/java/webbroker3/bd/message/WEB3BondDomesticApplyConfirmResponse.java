head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������m�F���X�|���X(WEB3BondDomesticApplyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 ���f��No.227
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (����������m�F���X�|���X)<BR>
 * ����������m�F���X�|���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyConfirmResponse
    extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String id;

    /**
     * (���񗘎q�����z)<BR>
     * ���񗘎q�����z<BR>
     */
    public String initialInterestAdjustAmount;

    /**
     * (��n���z)<BR>
     * ��n���<BR>
     */
    public String deliveryPrice;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date executionUpdateDate;

    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;

    /**
     * (����������m�F���X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 466661A403D6
     */
    public WEB3BondDomesticApplyConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondDomesticApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
