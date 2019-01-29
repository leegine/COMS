head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.12.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3MarginTransferService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋��ւ̐U�փT�[�r�X(WEB3MarginTransferService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/12 �����F (���u) �V�K�쐬 �d�l�ύX���f��736 739
Revision History : 2007/07/28 �Ј��� (���u) �d�l�ύX���f��741
*/
package webbroker3.aio.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (�ۏ؋��ւ̐U�փT�[�r�X)<BR>
 * @@author �����F
 * @@version 1.0
 */
public interface WEB3MarginTransferService extends Service
{
    /**
     * (submit�ۏ؋��U��)<BR>
     * �ۏ؋��ւ̐U�փT�[�r�X�������s���B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_dblCashinAmt - (�����z)<BR>
     * �����z<BR>
     * @@param l_strPassword - (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎�
     * @@throws WEB3BaseException
     */
    public void submitMarginTransfer(
        WEB3GentradeMainAccount l_mainAccount,
        Date l_datDeliveryDate,
        double l_dblCashinAmt,
        String l_strPassword,
        Trader l_trader) throws WEB3BaseException;
}
@
