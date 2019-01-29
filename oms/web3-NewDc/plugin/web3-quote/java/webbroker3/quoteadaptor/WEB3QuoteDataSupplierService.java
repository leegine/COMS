head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataSupplierService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �����T�[�r�X�C���^�[�t�F�[�X(WEB3DefaultQuoteDataSupplierServiceImpl.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/02 �R�c�@@��i(FLJ) �V�K�쐬
                    : 2009/01/28 ���@@�@@��(FLJ) CSK�������`�F�b�N�̋����Ή��̂��߁A���\�b�h�ǉ�
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.*;

/**
 * <p>
 * WebBroker3�Ŏg�p���鎞���T�[�r�X�̃C���^�[�t�F�[�X�B<br>
 * </p>
 * <p>
 * �p�����[�^��<code>RealType<code>���w�肵�āA���������擾�ł���悤�ɂ��邽�߁A
 * �e�g���[�f�B���O���W���[�����񋟂���<code>QuoteDataSupplierService</code>
 * �̃C���^�[�t�F�[�X���g�����Ă���B<br>
 * �e�g���[�f�B���O���W���[���ɒ�`�ς݂̎�������݂̂��w�肵�āA
 * ���������擾���郁�\�b�h�ł́A<code>RealType.REAL<code>�̎��������擾����B<br>
 * <br>
 * �܂��A���o���ϊ����ȂǊe��w���̎������擾�C���^�[�t�F�[�X���񋟂���B
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 * @@see webbroker3.quoteadaptor.RealType
 */
public interface WEB3QuoteDataSupplierService
    extends EqTypeQuoteDataSupplierService, IfoQuoteDataSupplierService
{

    /**
     * �����̎��������擾����B
     * 
     * @@param tradedProduct ���������擾���銔���̎�������B
     * @@param realType ���A���敪
     * @@return �����̎������B
     * @@throws NotFoundException
     */
    public WEB3EqTypeQuoteData getEqTypeQuote(
        EqTypeTradedProduct tradedProduct,
        RealType realType)
        throws NotFoundException;

    /**
     * �����w���敨�E�I�v�V�����̎��������擾����B
     * 
     * @@param tradedProduct ���������擾���銔���w���敨�E�I�v�V�����̎������
     * @@param realType ���A���敪
     * @@return �����w���敨�E�I�v�V�����̎������
     * @@throws NotFoundException
     */
    public WEB3IfoQuoteData getIfoQuote(
        IfoTradedProduct tradedProduct,
        RealType realType)
        throws NotFoundException;

    /**
     * ���ۓI�Ȗ������N���X�p�̃��\�b�h
     * 
     * @@param tradedProduct ���������擾��������̎�������B
     * @@param realType ���A���敪
     * @@return �������
     * @@throws NotFoundException
     */
    public QuoteData getQuote(TradedProduct tradedProduct, RealType realType)
        throws NotFoundException;
    
    /**
     * ������񂪑��݂��邩�ǂ����`�F�b�N�p�̃��\�b�h<BR>
     * �������Ȃ��ꍇ�Anull��Ԃ�<BR>
     * 
     * @@param tradedProduct ���������擾��������̎�������B
     * @@param realType ���A���敪
     * @@return �������
     * @@throws NotFoundException
     */
    public QuoteData getQuoteForCheck(TradedProduct tradedProduct, RealType realType)
        throws NotFoundException;

    /**
     * �w���̎��������擾����B
     * 
     * @@param indexType �w�����
     * @@param realType ���A���敪
     * @@return �w���̎������
     * @@throws NotFoundException
     */
    public WEB3IndexQuoteData getIndexQuote(
        IndexType indexType,
        RealType realType)
        throws NotFoundException;

}
@
