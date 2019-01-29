head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteCacheStore.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

/**
 * �L���b�V�����ꂽ���������擾���邽�߂̃C���^�[�t�F�C�X�B
 *
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3QuoteCacheStore
{

    /**
     * �L���b�V�����ꂽ�����̎��������擾����B
     *
     * @@param tradedProduct ���������擾����������
     * @@param realType ���A���敪
     * @@return �������
     */
    public WEB3EqTypeQuoteData get(
        EqTypeTradedProduct tradedProduct,
        RealType realType);
    
    /**
     * �L���b�V�����ꂽ�w���敨�E�I�v�V�����̎��������擾����B
     * 
     * @@param tradedProduct ���������擾����������
     * @@param realType ���A���敪
     * @@return �������
     */
    public WEB3IfoQuoteData get(
        IfoTradedProduct tradedProduct,
        RealType realType);
    
    /**
     * �L���b�V�����ꂽ�����̎��������擾����B
     * �����̎�����񂪑��݂��Ȃ��ꍇ�Anull��Ԃ��B
     *
     * @@param tradedProduct ���������擾����������
     * @@param realType ���A���敪
     * @@return �������
     */
    public WEB3EqTypeQuoteData getQuote(
        EqTypeTradedProduct tradedProduct,
        RealType realType);
    
    /**
     * �L���b�V�����ꂽ�w���敨�E�I�v�V�����̎��������擾����B
     * �����̎�����񂪑��݂��Ȃ��ꍇ�Anull��Ԃ��B
     * 
     * @@param tradedProduct ���������擾����������
     * @@param realType ���A���敪
     * @@return �������
     */
    public WEB3IfoQuoteData getQuote(
        IfoTradedProduct tradedProduct,
        RealType realType);
    
    /**
     * �L���b�V�����ꂽ�w���̎��������擾����B
     * 
     * @@param tradedProduct ���������擾����������
     * @@param realType ���A���敪
     * @@return �������
     */
    public WEB3IndexQuoteData get(IndexType indexType, RealType realType);

}@
