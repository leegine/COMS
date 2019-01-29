head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoProductInfoService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�������쐬�T�[�r�X�C���^�t�F�C�X(WEB3IpoProductInfoService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.message.WEB3IPOProductInfo;

/**
 * IPO�������쐬�T�[�r�X�C���^�t�F�C�X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public interface WEB3IpoProductInfoService extends Service 
{
    
    /**
     * (createIPO�������)<BR>
     * ������IPO����ID�ɊY������IPO�����̓��e�ŁA<BR>
     * IPO������񃁃b�Z�[�W�f�[�^�C���X�^���X���쐬����B<BR>
     * @@param l_lngProductId - IPO�����h�c
     * @@return webbroker3.ipo.message.WEB3IpoProductInfo
     * @@throws WEB3BaseException
     * @@roseuid 40C66AFE03D5
     */
    public WEB3IPOProductInfo createIpoProductInfo(long l_lngProductId) throws WEB3BaseException;
    
    /**
     * (createIPO����)<BR>
     * ��ʓ��͒l���IPO���������쐬����B<BR>
     * 
     * @@param l_ipoProductParams - IPO�����s�I�u�W�F�N�g
     * @@param l_productInfoInputMsg - IPO���������̓��b�Z�[�W
     * @@param l_admin - �Ǘ��҃I�u�W�F�N�g
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40CE7934029A
     */
    public WEB3IpoProductImpl createIpoProduct(IpoProductParams l_ipoProductParams, WEB3IPOProductInfo l_productInfoInputMsg, WEB3Administrator l_admin);
}
@
