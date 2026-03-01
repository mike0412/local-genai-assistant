# Local RAG Assistant (Java)

本プロジェクトは、Java で実装したローカル RAG（Retrieval-Augmented Generation）システムです。

## 特徴
- Ollama + ローカル LLM 対応
- 日本語 PDF → Chunk → Embedding → 検索
- In-memory Vector Store（FAISS / Milvus 置換可能）
- 企業向け構成（Agent / Retriever / Tool 分離）

## アーキテクチャ
PDF → Chunk → Embedding → VectorStore → Retriever → LLM

## 想定用途
- 社内ドキュメント検索
- 技術仕様 Q&A
- テストケース生成


# Local RAG Assistant (Java)

This project is a production-style RAG (Retrieval-Augmented Generation)
system implemented in Java.

## Features
- Local LLM via Ollama
- Japanese PDF ingestion
- Chunking + Embedding + Retrieval
- Modular architecture (Agent / Retriever / VectorStore)

## Architecture
PDF → Chunk → Embedding → Vector Store → Retriever → LLM

## Use Cases
- Internal document Q&A
- Knowledge search
- Test case generation
