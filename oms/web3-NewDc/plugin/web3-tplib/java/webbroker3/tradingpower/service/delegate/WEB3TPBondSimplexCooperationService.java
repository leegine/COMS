head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPBondSimplexCooperationService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���V���v���N�X�A�g�T�[�r�X(WEB3TPBondSimplexCooperationService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/26 ���z(���u) �V�K�쐬 ���f��No.276,278,287
*/
package webbroker3.tradingpower.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * (���V���v���N�X�A�g�T�[�r�X)<BR>
 * �i���V���v���N�X�A�g�T�[�r�X�j<BR>
 *
 * @@author ���z
 * @@version 1.0
 */
public interface WEB3TPBondSimplexCooperationService extends Service
{
    /**
     * (save�����t���)<BR>
     * �isave�����t����j<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * �i����ID�j<BR>
     * @@param l_dblRestraint - (�����t���)<BR>
     * �i�����t����j<BR>
     * @@param l_datFinTransactionDate - (�g�����U�N�V����������)<BR>
     * �i�g�����U�N�V�����������j<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * �i��n���j<BR>
     * @@param l_strOrderNo - (����No)<BR>
     * �i����No�j<BR>
     * @@throws WEB3BaseException
     */
    public void saveBondBuyAmount(long l_lngAccountId, double l_dblRestraint,
        Date l_datFinTransactionDate, Date l_datDeliveryDate, String l_strOrderNo) throws WEB3BaseException;

    /**
     * (cancel�����t���)<BR>
     * �icancel�����t����j<BR>
     * <BR>
     * @@param l_strOrderNo - (����No)<BR>
     * �i����No�j<BR>
     * @@throws WEB3BaseException
     */
    public void cancelBondBuyAmount(String l_strOrderNo) throws WEB3BaseException;

    /**
     * (get���Y�]���z�ꗗ)<BR>
     * �iget���Y�]���z�ꗗ�j<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * (�⏕����)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAssetList(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException;

}
@
