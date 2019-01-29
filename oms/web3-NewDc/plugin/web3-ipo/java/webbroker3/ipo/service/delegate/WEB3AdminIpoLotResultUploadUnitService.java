head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I���ʃA�b�v���[�h�P���T�[�r�X�C���^�t�F�C�X(WEB3AdminIpoLotResultUploadUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.ipo.WEB3IpoOrderImpl;


/**
 * �Ǘ���IPO���I���ʃA�b�v���[�h�P���T�[�r�X�C���^�t�F�C�X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public interface WEB3AdminIpoLotResultUploadUnitService extends Service 
{
    
    /**
     * ���I���ʂ��X�V����B
     * @@param l_orderList - IPO�\����ArrayList
     * 
     * @@param l_isNewLottery - �V�K���I���ǂ����̔���<BR>
     * <BR>
     * �@@true�F�@@�V�K���I<BR>
     * �@@false�F�@@�J�㒊�I
     * @@param l_administrator - �Ǘ��҃I�u�W�F�N�g
     * 
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@param l_strLotResult - ���I����
     * @@param l_dblElectedQuantity - ���I����
     * @@param l_lngSubstitutePriority - �D�揇��
     * @@roseuid 40F61BB00292
     */
    public void updateLotResult(
        ArrayList l_orderList, 
        boolean l_isNewLottery, 
        WEB3Administrator l_administrator, 
        MainAccount l_mainAccount, 
        String l_strLotResult, 
        double l_dblElectedQuantity, 
        Long l_lngSubstitutePriority) throws WEB3BaseException;
        
    /**
     * ���I���ʂ��X�V����B<BR>
     * @@param l_order - IPO�\���I�u�W�F�N�g
     * 
     * @@param l_isNewLottery - �V�K���I���ǂ����̔���<BR>
     * <BR>
     * �@@true�F�@@�V�K���I<BR>
     * �@@false�F�@@�J�㒊�I
     * @@param l_administrator - �Ǘ��҃I�u�W�F�N�g
     * 
     * @@param l_strLotResult - ���I����
     * @@param l_dblElectedQuantity - ���I����
     * @@param l_lngSubstitutePriority - �D�揇��
     * @@roseuid 40F64C8F02B1
     */
    public void updateLotResult(
        WEB3IpoOrderImpl l_order, 
        boolean l_isNewLottery, 
        WEB3Administrator l_administrator, 
        String l_strLotResult, 
        double l_dblElectedQuantity, 
        Long l_lngSubstitutePriority) throws WEB3BaseException;
}
@
