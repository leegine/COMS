head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3OrderReqNumberHead2ManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :���ʃR�[�h���Q������T�[�r�X�N���X(WEB3OrderReqNumberHead2ManageServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.boot.*;

/**
 * ���ʃR�[�h���Q������T�[�r�X�N���X
 *
 * @@author ��
 * @@version 1.0
 */
public interface WEB3OrderReqNumberHead2ManageService
    extends Service
{

    /**
     * ���ʃR�[�h���Q���擾����
     */
    public String getOrderReqNumberHead2();

    public static final String NOT_GET_NUMBER_FLAG ="web3.not.get.number.flag";
}
@
