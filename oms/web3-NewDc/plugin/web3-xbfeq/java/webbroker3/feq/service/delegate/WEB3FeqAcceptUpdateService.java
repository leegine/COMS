head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAcceptUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������t�X�V�T�[�r�X(WEB3FeqAcceptUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 ������(���u) �V�K�쐬
                 : 2005/07/26 ���U(���u) ���r���[
                 : 2006/12/19 ���G��(���u) ���f���@@No.314�Ή�
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import webbroker3.common.WEB3BaseException;

/**
 * (�O��������t�X�V�T�[�r�X) <BR>
 * �O��������t�X�V�T�[�r�X�C���^�t�F�C�X
 * 
 * @@ author ������ 
 * @@ version 1.0
 */
public interface WEB3FeqAcceptUpdateService extends Service
{
    
    /**
     * (update��t) <BR>
     * ������t�X�V�������s���B
     * @@param l_marketResponseMessage - (�s�ꃌ�X�|���X���b�Z�[�W)
     * @@throws WEB3BaseException
     * @@roseuid 42A579180083
     */
    public void updateAccept(MarketResponseMessage l_marketResponseMessage) throws WEB3BaseException;

    /**
     * (get�s�ꃌ�X�|���X���b�Z�[�W)<BR>
     * �ύX���t�敪�ɑΉ�����s�ꃌ�X�|���X���b�Z�[�W�𐶐����ԋp����B<BR>
     * <BR>
     * @@param l_lngOrderId - (�����h�c)<BR>
     * �����h�c<BR>
     * @@param l_strAfterChangeAcceptDiv - (�ύX���t�敪)<BR>
     * �ύX���t�敪<BR>
     * <BR>
     * 01�F������t�� <BR>
     * 02�F������t�G���[ <BR>
     * 03�F������t�ώ��<BR>
     * <BR>
     * 11�F������ <BR>
     * 12�F�����G���[<BR>
     * <BR>
     * 21�F����� <BR>
     * 22�F����G���[<BR>
     * <BR>
     * 31�F�o����<BR>
     * @@return MarketResponseMessage
     */
    public MarketResponseMessage getMarketResponseMessage(
        long l_lngOrderId, String l_strAfterChangeAcceptDiv);
}
@
