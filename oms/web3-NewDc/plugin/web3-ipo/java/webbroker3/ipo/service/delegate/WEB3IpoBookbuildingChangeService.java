head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.33.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�����T�[�r�X�C���^�t�F�C�X�N���X(WEB3IpoBookbuildingChangeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ���]��(���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * IPO�u�b�N�r���f�B���O�����T�[�r�X�C���^�t�F�C�X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public interface WEB3IpoBookbuildingChangeService extends WEB3BusinessService 
{
    
    /**
     * IPO�u�b�N�r���f�B���O�������������{����B
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D96872006D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
